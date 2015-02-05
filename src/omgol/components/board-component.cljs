(ns omgol.components.board-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.game.logic :as game-logic]
            [omgol.drawing :as drawing]))

(defn board-component [app owner]
  (reify
    om/IDidUpdate
    (did-update [_ _ _]
      (let [canvas (om/get-node owner)
            game   (get-in app [:main-app :game])]
        (game-logic/draw-world game canvas)))

    om/IDidMount
    (did-mount [_]
      (let [canvas (om/get-node owner)
            game   (get-in app [:main-app :game])]
        (game-logic/draw-world game canvas)))

    om/IRender
    (render [_]
      (let [board-width  (get-in app [:main-app :game :board-width])
            board-height (get-in app [:main-app :game :board-height])]
        (omdom/canvas #js {:className "playground"
                           :width board-width
                           :height board-height})))))
