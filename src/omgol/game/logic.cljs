(ns omgol.game.logic
  (:require [om.core :as om :include-macros true]
            [omgol.drawing :as drawing]))

(defn ^{:private true} draw-grid [game canvas]
  (let [width     (get game :board-width)
        height    (get game :board-height)
        cell-size (get game :cell-size)
        grid      (get game :grid-color)]
    (drawing/draw-grid canvas width height cell-size grid)))

(defn ^{:private true} draw [cell-size game canvas]
  (let [alive     (get game :alive-color)
        cells     (get game :alive-cells)]
    (drawing/clear-canvas canvas)
    (doseq [pair cells]
      (drawing/fill-cell (first pair) (second pair) cell-size alive canvas))))

(defn draw-world [game canvas]
  (let [cell-size (get game :cell-size)]
    (draw cell-size game canvas)
    (draw-grid game canvas)))

(defn draw-preview [game canvas]
  (let [cell-size (get game :preview-cell-size)]
    (draw cell-size game canvas)))

(defn ^{:private true} glider-pattern [x y]
  "You don't know how the 'glider' looks?
   Here you are!

   .....
   ..*..
   ...*.
   .***.
   ....."

  [[x (- y 1)]
   [(+ x 1) y]
   [(- x 1) (+ y 1)]
   [x (+ y 1)]
   [(+ x 1) (+ y 1)]])

(defn create-glider [game]
  (let [board-width  (get game :board-width)
        board-height (get game :board-height)
        cell-size    (get game :cell-size)
        cells-x      (/ board-width cell-size)
        cells-y      (/ board-height cell-size)
        x            (+ (rand-int (- cells-x 2)) 1)
        y            (+ (rand-int (- cells-y 2)) 1)]
    (om/transact! game :alive-cells #(apply conj % (glider-pattern x y)))))
