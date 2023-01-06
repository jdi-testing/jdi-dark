import requests

query = {
        'key': '3445103a21ddca2619eaceb0e833d0db',
        'token': 'a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0'
}

def get_boards():
    url = "https://api.trello.com/1/members/me/boards"

    response = requests.request(
        "GET",
        url,
        params=query
    )
    return response.json()

def clean_board(board_id):
    url = "https://api.trello.com/1/boards/"

    response = requests.request(
        "DELETE",
        url+board_id,
        params=query
    )

def clean_boards():
    board_list = get_boards()
    for board in board_list:
        print(board)
        clean_board(board['id'])

# run in case of error that user have too many boards
if __name__ == '__main__':
    clean_boards()

