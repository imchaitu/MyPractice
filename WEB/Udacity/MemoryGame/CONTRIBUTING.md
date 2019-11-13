# How to contribute
Currently the code is not in any repository. If you are interested, you are free to host a repository and upload this code and publish.

## Instructions for development
- Maintain responsiveness.
  - It is currently achieved by using the `<meta name="viewport" content="width=device-width, initial-scale=1.0">` tag in _index.html_ and
  - Using all relative lengths ( `vmin` ) in css.
    - `vmin` is use everywhere to make the game fit inside the screen without scrolling no matter what the aspect ratio is.
- Current animations look good only if the cards are in square shape. If you want to change the shape of cards to rectangular, I would suggests you to change animations too as the spin looks not so good for rectangular cards.
