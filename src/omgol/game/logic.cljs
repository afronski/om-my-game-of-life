(ns omgol.game.logic
  (:require [omgol.drawing :as drawing]))

(defn initialize-world [game-settings canvas]
  (let [context   (drawing/extract-context canvas)
        width     (get game-settings :board-width)
        height    (get game-settings :board-height)
        cell-size (get game-settings :cell-size)
        color     (get game-settings :grid-color)]
    (drawing/draw-grid context width height cell-size color)))
