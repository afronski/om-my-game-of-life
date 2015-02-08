(ns omgol.components.board-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.game.logic :as game-logic]
            [omgol.drawing :as drawing]))

(defn board-component [app owner]
  (defn toggle-cell [event]
    (let [cells            (get-in app [:main-app :game :alive-cells])
          simulation-state (get-in app [:main-app :game :simulation-state])
          mouse-x          (.-pageX event)
          mouse-y          (.-pageY event)
          canvas           (om/get-node owner)
          canvas-x         (.-offsetLeft canvas)
          canvas-y         (.-offsetTop canvas)
          pixel-x          (- mouse-x canvas-x)
          pixel-y          (- mouse-y canvas-y)
          history          (get-in app [:main-app :game :history])
          cell-size        (get-in app [:main-app :game :cell-size])
          x                (quot pixel-x cell-size)
          y                (quot pixel-y cell-size)]
      (when (and (not simulation-state)
                 (= (count history) 0))
        (if (= (some #(= [x y] %) cells) true)
          (om/transact! cells (fn [old] (vec (remove #(= [x y] %) old))))
          (om/transact! cells #(conj % [x y]))))))

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
                           :height board-height
                           :onClick toggle-cell})))))
