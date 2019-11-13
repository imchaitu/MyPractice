# Memory Game Project
A simple but entertaining game base on HTML, CSS and JavaScript. You don't need anything more than a browser and internet to play.

## Table of Contents
* [Installation](#installation)
* [Instructions to play](#instructions-to-play)
* [Contributing](#Contributing)

## Installation
##### Prerequisites:
- Browser (Chrome and Firefox recommended).
- Internet access.

##### Dependencies:
- You will need to access internet to access as a couple of _CSS_ styles are being fetched over internet:
  - [font-awesome](https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css)
  - [goole fonts](https://fonts.googleapis.com/css?family=Coda)

##### Process:
Just download the .zip project file, extract it and open the **index.html** file in a browser.

## Instructions to play
1. When you start game:
  - Start rating will be 3.
  - Moves will be 0.
  - A timer starts.
  - All the cards are turned down.
2. Click on a card, will open and show the symbol the card has with a flip animation.
  - The card stays open till a next card is open.
3. Click on a second card, will open the card in a similar way. But this time (for every even card opened):
  - An attempt is made to check the already opened card is matching the current card.
    - If matched,
      - Both the cards show a bumpy animation.
      - Both the cards stay open.
      - They are not clickable anymore, i.e., you cannot close any of the matched cards by clicking on them.
    - If not matched,
      - Both cards show spin animation.
      - Both cards turn back to closed position.
      - They will be clickable, i.e., you can open any of them to attempt a match again with any closed card.
  - Moves will be increased by one.
4. Star rating is based on number of _moves_ you consume to complete the game. After consuming certain number of moves, you can see stars getting reduced already.
5. Once all the cards are matched,
  - Time will be stopped.
  - A score card will be shown with the details of the **moves** you have consumed, **stars** you acquired, and **time consumed** to complete the game.
  - A **Play Again** button will be shown, by clicking which the game will be displayed again with all the setting reset and ready to start again.
6. If you want to reset the game in the middle, you can do it by clicking on the **reset** icon button which at the right end in the same line stars, moves and timer are shown.

## Contributing
Currently the code is not in any repository. If you are interested, you are free to host a repository and upload this code and publish.

For more details and instructions, check out [CONTRIBUTING.md](CONTRIBUTING.md).
