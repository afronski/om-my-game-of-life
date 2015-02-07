(ns omgol.drawing)

(defn ^{:private true} extract-context [canvas]
  (.getContext canvas "2d"))

(defn ^{:private true} draw-pixel [x y size color context]
  (set! (.-fillStyle context) color)
  (.fillRect context x y size size))

(defn clear-canvas [canvas]
  (let [context (extract-context canvas)
        width   (.-width canvas)
        height  (.-height canvas)]
    (.clearRect context 0 0 width height)))

(defn fill-cell [cell-x cell-y size color canvas]
  (let [x       (* cell-x size)
        y       (* cell-y size)
        context (extract-context canvas)]
    (draw-pixel x y size color context)))

(defn draw-grid [canvas width height pixel-size color]
  (let [screen-canvas-width  (* width pixel-size)
        screen-canvas-height (* height pixel-size)
        context              (extract-context canvas)]

    (.save context)

    (.translate context 0.5 0.5)

    (set! (.-strokeStyle context) color)
    (set! (.-lineWidth context) 1)

    (dotimes [y height]
      (let [pixel-y (* y pixel-size)]
        (doto context
          (.beginPath)
          (.moveTo 0 pixel-y)
          (.lineTo screen-canvas-width pixel-y)
          (.stroke))))

    (dotimes [x width]
      (let [pixel-x (* x pixel-size)]
        (doto context
          (.beginPath)
          (.moveTo pixel-x 0)
          (.lineTo pixel-x screen-canvas-height)
          (.stroke))))

    (.restore context)))
