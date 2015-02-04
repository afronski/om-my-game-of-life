(ns omgol.components.board-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.game.logic :as game-logic]
            [omgol.drawing :as drawing]))

(defn board-component [app owner]
  (reify
    om/IDidMount
    (did-mount [_]
      (game-logic/initialize-world (get-in app [:main-app :game]) (om/get-node owner))
      (drawing/fill-cell 1 1 10 "rgb(0,0,0)" (drawing/extract-context (om/get-node owner))))
    om/IRender
    (render [_]
      (let [board-width  (get-in app [:main-app :game :board-width])
            board-height (get-in app [:main-app :game :board-height])]
        (omdom/canvas #js {:className "playground"
                           :width board-width
                           :height board-height})))))
