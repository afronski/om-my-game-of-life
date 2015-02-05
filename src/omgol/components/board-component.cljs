(ns omgol.components.board-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.game.logic :as game-logic]
            [omgol.drawing :as drawing]))

(defn board-component [app owner]
  (reify
    om/IDidMount
    (did-mount [_]
      (let [canvas        (om/get-node owner)
            game-settings (get-in app [:main-app :game])
            cell-size     (get game-settings :cell-size)
            color         (get game-settings :alive-color)]
        (game-logic/initialize-world game-settings canvas)
        (drawing/fill-cell 1 1 cell-size color canvas)))

    om/IRender
    (render [_]
      (let [board-width  (get-in app [:main-app :game :board-width])
            board-height (get-in app [:main-app :game :board-height])]
        (omdom/canvas #js {:className "playground"
                           :width board-width
                           :height board-height})))))
