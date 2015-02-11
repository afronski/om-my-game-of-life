# Notes

Move to tag - `git checkout TAG_NAME`
Move to master - `git reset . && git checkout . && git checkout master`

`lein cljsbuild auto`
`lein cljsbuild auto dev`

## OM_COMPONENTS_START

- ClojureScript - Syntax.
- ClojureScript - Look and Feel.
- Directory Structure.
- `index.html` - Overview.
  - Components on the page.
  - Few words about Google Closure Compiler.
- `project.clj`
  - Few words about `leiningen`
  - Show commands
- `main.cljs` - `om/root`
- `app-state.cljs`
  - Why storing whole state?
  - What is an atom?
    - http://www.javiersaldana.com/images/epochal-time-model.jpg
- `footer-component.cljs`
  - `reify`
  - `IRender` - protocol which defines method which will be called
    when application state or local state changes. In *React.js* it
    corresponds to the `render()` method.
  - `omdom/*` - Alternative way to building views.
- JavaScript interop.
  - `(pr-str :a)`
  - `(.log js/console "Test")`

## OM_COMPONENTS_LIFECYCLE

- `board-component.cljs`
  - `IDidMount` - protocol with methods invoked once, after initial
    rendering.
  - At this moment we have DOM nodes attached.
  - In *React.js* corresponding method is called `componentDidMount()`.
- http://fw008950-flywheel.netdna-ssl.com/wp-content/uploads/2014/07/Screen-Shot-2014-07-22-at-15.30.33.png


## OM_COMPONENTS_SUBVIEW

- `menu-component.cljs`
  - `om/build`
  - Composite components are very nice abstraction.
- `drawing.cljs`
  - Drawing grid.
  - Filling cells.

## OM_COMPONENTS_EVENTS_AND_STATE_HANDLING

- `menu-component.cljs`
  - How to attach event handlers?
  - Local state.
  - `IRenderState` - the only difference is the state passed to the
    rendering function.
  - Closures rules are the same like in JavaScript (Component is one
    big closure).
  - State Manipulation:
    - `om/transact!`
    - `om/update!`

## OM_COMPONENTS_EVENT_HANDLERS

- `board-component.cljs`
  - Drawing single cell.
    - Internals:
      - JavaScript - Canvas interop.
  - `IDidUpdate` - protocol methods called when React rendered
    component after state update.
  - Event Handlers.
  - Styling.

## OM_COMPONENTS_BUILD_ALL

- `events-list-component.cljs`
  - Composite components.
  - Power of functional programming `apply omdom/ul ... om/build-all`.

## OM_COMPONENTS_UNDO_REDO_AND_ATOM_STATE_MANAGEMENT

- `app-state.cljs` - History.
- `simulation.cljs` - Manipulating state outside of rendering loop.
  - `add-watch` facility.
- `rules.cljs` - Calculating neighbours, Game of Life rules.
- Undo / Redo.
  - Thanks to the state management we have very lean implementation of undo / redo.
  - Storing it in the global application state? What about memory?
    - Structural sharing.
    - Persistent Data Structures.
      - http://eclipsesource.com/blogs/wp-content/uploads/2009/12/clojure-trees.png
  - Show the github diff which shows the important pieces of undo / redo.
