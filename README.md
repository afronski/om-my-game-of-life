# om-my-game-of-life

## Motivation

I always wanted to do something with use of *Om* and *ClojureScript*, and here it is. With high probability it is not an idiomatic Clojure code, you have been warned. :wink:

`cljs + om = ♥`

It is a part of lecture for Meet.js meeting in Katowice (performed at 11.02.2015). You can find slides [here](http://afronski.pl/presentations/).

## How to start?

First, you need to compile the *ClojureScript* to *JS* with:

```bash
$ lein cljsbuild auto
```

The option `auto` means that it will automatically recompile when any script will change. Then you can open file from `resources/index.html` in your browser of choice.

## TODO

- Preview component.
- Undo / Redo, State Change item.
- Start / Pause, Clear, Put random glider on board.
- Game of Life logic with nicer delay.
- Drawing game state.
- Mapping game state to events.
- Previewing on hover for each state change item.
