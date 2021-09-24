package com.epam.jdi.dto;

import com.jdiai.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization extends DataClass<Organization> {
    public String id, name, displayName, desc, url, website, logoHash;
    private List<String> idBoards, invitations, powerUps, premiumFeatures, products;
    private boolean invited;
    private List<Membership> memberships;
    private Prefs prefs;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Prefs extends DataClass<Prefs> {
        public String permissionLevel, associatedDomain;
        public List<String> orgInviteRestrict;
        public boolean externalMembersDisabled;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class BoardVisibilityRestrict extends DataClass<BoardVisibilityRestrict> {
            @JsonProperty("private")
            public String privateVisibility;
            @JsonProperty("org")
            public String orgVisibility;
            @JsonProperty("public")
            public String publicVisibility;
        }
    }
}
