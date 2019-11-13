/**
 * Global variables
 */
let deck = document.querySelector('.deck');
let cards = Array.from(deck.childNodes);
let openCards = [];
let closedCards = deck.childElementCount;
let moves = 0;
let stars = 3;
let movesEl = document.querySelector('span.moves');
let totalSeconds = 0;
let timer = null;

/**
 * This function is run for the first time when the page is loaded.
 * Resets all the cards to closed and shuffles the cards randomly everytime the page is loaded.
 * @return {undefined} Returns nothing
 * function initGame() {
 *  reset();
 * }
 */


/*
 * Display the cards on the page
 *   - shuffle the list of cards using the provided "shuffle" method below
 *   - loop through each card and create its HTML
 *   - add each card's HTML to the page
 */
let initGame = function reset() {
  moves = 0;
  stars = 3;
  openCards = [];
  movesEl.innerText = moves;
  cards = shuffle(cards);
  const tempDC = document.createDocumentFragment();
  cards.forEach(function(el, ix, els) {
    el.classList = "card";
    tempDC.appendChild(el);
  });
  deck.innerHTML = ""
  deck.appendChild(tempDC);
  closedCards = deck.childElementCount;
  document.querySelectorAll('.stars i').forEach(function(el) {
    el.classList = 'fa fa-star';
  });
  document.querySelector('.timer').textContent = "00:00";
  if (timer !== null) {
    clearInterval(timer);
  }
  totalSeconds = 0
  timer = setInterval(updatePassedTime, 1000);
}

/**
 * Incriments the total seconds, converts the seconds into MM:SS format and update the timer element.
 * This function is called by setInterval
 * @return {undefined} [description]
 */
function updatePassedTime() {
  totalSeconds += 1;
  let minutes = Math.round(totalSeconds / 60) + "";
  let seconds = totalSeconds % 60 + "";
  minutes = minutes.length < 2 ? "0" + minutes : minutes;
  seconds = seconds.length < 2 ? "0" + seconds : seconds;
  document.querySelector('.timer').textContent = minutes + ":" + seconds;
}

/**
 * Called by 'click' eventlistner of the 'PlayAgain' button on the
 * score card (which is displayed after the game is completed successfullly)
 * @return {undefined} [description]
 */
function showGame() {
  initGame();
  document.querySelector('.container').style.display = 'flex';
  document.querySelector('.win_card').style.display = 'none';
  deck.addEventListener('click', deckEvtListner);
}


/**
 * Shuffles all the cards and returns the deck of cards in new order.
 * Shuffle function from http://stackoverflow.com/a/2450976
 * @param  {Array} array Array of cards in the deck.
 * @return {Array}       Array of deck of cards in new order.
 */
function shuffle(array) {
  var currentIndex = array.length,
    temporaryValue, randomIndex;

  while (currentIndex !== 0) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

/*
 * set up the event listener for a card. If a card is clicked:
 *  - display the card's symbol (put this functionality in another function that you call from this one)
 *  - add the card to a *list* of "open" cards (put this functionality in another function that you call from this one)
 *  - if the list already has another card, check to see if the two cards match
 *    + if the cards do match, lock the cards in the open position (put this functionality in another function that you call from this one)
 *    + if the cards do not match, remove the cards from the list and hide the card's symbol (put this functionality in another function that you call from this one)
 *    + increment the move counter and display it on the page (put this functionality in another function that you call from this one)
 *    + if all cards have matched, display a message with the final score (put this functionality in another function that you call from this one)
 */

/**
 * Shows the card open when you click on it. Called by 'click' eventlistner on 'deck' element.
 * Also adds element to the openCards.
 * @param  {Element} el The element on which 'click' occured
 * @return {undefined}    [description]
 */
function showCard(el) {
  el.classList = 'card open show flip-card-ani';
  openCards.push(el);
}

/**
 * Check the openCards if they are matching.
 * Called when the every second card is opened.
 * @return {boolean} True or False depending on the cards in open stare are matching or not.
 */
function checkCardForMatch() {
  let match = false;
  if (openCards.length !== 2) {
    console.error('Number of open cards is ' + openCards.lenght + ' which is not 2');
    console.error('Cannot perform matching');
  } else {
    openCards[0].firstElementChild.classList[1] === openCards[1].firstElementChild.classList[1] ?
      match = true : match = false;
  }
  return match;
}

/**
 * Locks the cards i.e., puts the cards in open state, if they are matched.
 * Match condition is determined by checkCardForMatch function. Called when every second card is open 'and' the open cards mathces.
 * @return {undefined} [description]
 */
function lockMachedCards() {
  openCards.forEach(function(el) {
    el.classList = 'card match';
  });
  closedCards -= 2;
  openCards = [];
  if (closedCards === 0) {
    showWindCard();
  } else {
    deck.addEventListener('click', deckEvtListner);
  }
}

/**
 * Closes the open cards if they both don't match.
 * Called when every second card is open and the open cards don't match.
 * @return {undefined} [description]
 */
function closeUnmatchedCards() {
  openCards.forEach(function(el) {
    el.classList = 'card';
  });
  openCards = [];
  deck.addEventListener('click', deckEvtListner);
}

/**
 * Handles the increment of moves and decrement of stars basing on moves.
 * Also updates the respective elements.
 * @return {undefined} [description]
 */
function handleMovesAndStars() {
  moves++;
  movesEl.innerText = moves;
  if (moves === 10 || moves === 15) {
    let starsEls = document.querySelectorAll('.fa-star');
    starsEls.item(starsEls.length - 1).classList = 'fa fa-star-o';
    stars -= 1;
  }

}

/**
 * Perform animation immediatly after matching test is performed on open cards, but before they are closed/locked open.
 * @param  {String} how 'lock|close' -> only two accepted values specifying how the behaviour of animation should be.
 * @return {undefined}     [description]
 */
function animateCards(how) {
  deck.removeEventListener('click', deckEvtListner);
  if (how === 'lock') {
    openCards.forEach(function(el) {
      el.classList = 'card open show match-card-ani matched-cards';
    });
  } else if (how === 'close') {
    openCards.forEach(function(el) {
      el.classList = 'card open show fa-pulse unmatched-cards';
    });
  } else {
    console.error('The argument should be one of \'lock\' and \'close\'. Other values now meaning as of now');
  }
}

/**
 * Hides the game (all cards) and shows the score board once game is completed successfullly.
 * @return {undefined} [description]
 */
function showWindCard() {
  clearInterval(timer);
  let timePlayed = document.querySelector('.timer').textContent;
  document.querySelector('.score-stars').textContent = 'With ' + moves + ' Moves and ' + stars + ' Stars in ' + timePlayed.split(':')[0] + ' min ' + timePlayed.split(':')[1] + ' sec.'
  document.querySelector('.container').style.display = 'none';
  document.querySelector('.win_card').style.display = 'flex';
}

/**
 * Called by the 'click' even on the deck.
 * Though the event listner is on 'deck' element, in this function the inital check is to verify if the click is occured on
 * a card. Only if the click is occured on a card, actual functionality starts.
 * @param  {Event} evt The event object
 * @return {undefined}     [description]
 */
let deckEvtListner = function(evt) {
  let targetCard = null;
  if (evt.target.classList.contains('card')) {
    targetCard = evt.target;
  } else if (evt.target.classList.contains('fa')) {
    targetCard = evt.target.parentElement;
  }
  if (targetCard !== null && targetCard.classList.toString() === 'card') {
    showCard(targetCard);
    if (openCards.length === 2) {
      let cardsMached = checkCardForMatch();
      if (cardsMached) {
        animateCards('lock');
        setTimeout(lockMachedCards, 500);
      } else {
        animateCards('close');
        setTimeout(closeUnmatchedCards, 500);
      }
      handleMovesAndStars();
    }
  }
}

//Entrypoint
initGame();

/**
 * Event listners
 */
//Event listner listenning to click on cards (deck actually).
deck.addEventListener('click', deckEvtListner);

//Event listner for click on 'reset' button.
document.querySelector('.restart').addEventListener('click', function(evt) {
  initGame();
});

//Event listner for click on 'PlayAgain' button.
document.querySelector('.playAgain').addEventListener('click', function() {
  showGame()
});
