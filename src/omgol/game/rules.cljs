(ns omgol.game.rules)

(defn ^{:private true} kill-cell [x y state]
  (swap! state (fn [cells] (vec (remove #(= [x y] %) cells)))))

(defn ^{:private true} resurrect-cell [x y state]
  (swap! state #(conj % [x y])))

(defn ^{:private true} generate-coordinates [x y width height]
  (let [coordinates   [[-1 -1] [0 -1] [1 -1]
                       [-1  0]        [1  0]
                       [-1  1] [0  1] [1  1]]
        safe          (fn [v dv size] (rem (+ (+ v dv) size) size))
        to-coordinate (fn [ [dx dy] ] [(safe x dx width) (safe y dy height)])]
    (into #{} (map to-coordinate coordinates))))

(defn ^{:private true} count-neighbours [x y width height cells]
  (let [neighbours (generate-coordinates x y width height)]
    (count (filter neighbours cells))))

(defn single-step [state]
  (let [board-width    (get-in @state [:main-app :game :board-width])
        board-height   (get-in @state [:main-app :game :board-height])
        cell-size      (get-in @state [:main-app :game :cell-size])
        old-cells      (get-in @state [:main-app :game :alive-cells])
        cells          (atom (get-in @state [:main-app :game :alive-cells]))
        cells-x        (/ board-width cell-size)
        cells-y        (/ board-height cell-size)]
    (dotimes [x cells-x]
      (dotimes [y cells-y]
        (let [neighbours (count-neighbours x y cells-x cells-y old-cells)
              alive      (some #(= [x y] %) old-cells)]
          (if alive
            (when (or (< neighbours 2) (> neighbours 3))
              (kill-cell x y cells))
            (when (= neighbours 3)
              (resurrect-cell x y cells))))))
    (swap! state assoc-in [:main-app :game :alive-cells] @cells)))
