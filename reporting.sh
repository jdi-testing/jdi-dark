#!/bin/bash

function printAllureSummary() {
    echo "Brief passed/failed/broken/skipped summary by JDK: $1"

    content=$(<"allure-report/widgets/summary.json")     #file system request
    passed="$(echo "${content}"| jq '.statistic.passed')"
    failed="$(echo "${content}"| jq '.statistic.failed')"
    skipped="$(echo "${content}"| jq '.statistic.skipped')"
    broken="$(echo "${content}"| jq '.statistic.broken')"
    echo "${JDK}:"
    echo "  Passed:  ${passed}"
    echo "  Failed:  ${failed}"
    echo "  Broken:  ${skipped}"
    echo "  Skipped: ${broken}"

    echo "End of summary"
}

function generateAllureReports() {
    reportDirList="";
    allureDirExistence=false
    for report in $(ls -d1 jdi-dark*tests/target/allure-results)
    do
        allureDirExistence=true
        reportDirList="${reportDirList} ${report}"
    done
    if [[ "x${allureDirExistence}" == "xfalse" ]] ; then
        echo "Failed inside generateAllureReports()"
        exitWithError
    fi
    echo "Generating allure-report based on: ${reportDirList}"
    allure generate --clean ${reportDirList}

}