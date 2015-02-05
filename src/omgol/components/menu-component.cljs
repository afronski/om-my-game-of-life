(ns omgol.components.menu-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.drawing :as drawing]
            [omgol.game.logic :as game-logic]))

(defn preview [game owner]
  (reify
    om/IDidUpdate
    (did-update [_ _ _]
      (let [canvas (om/get-node owner)]
        (game-logic/draw-preview game canvas)))

    om/IDidMount
    (did-mount [_]
      (let [canvas (om/get-node owner)]
        (game-logic/draw-preview game canvas)))

    om/IRender
    (render [_]
      (let [board-width       (get game :board-width)
            board-height      (get game :board-height)
            preview-cell-size (get game :preview-cell-size)
            cell-size         (get game :cell-size)
            scale             (/ cell-size preview-cell-size)
            preview-width     (/ board-width scale)
            preview-height    (/ board-height scale)]
        (omdom/canvas #js {:className "preview"
                           :width preview-width
                           :height preview-height})))))

(defn controls [game owner]
  (defn toggle-simulation [_]
    (om/transact! game :simulation-state #(not %)))

  (defn clear-board [_]
    (om/update! game :alive-cells []))

  (defn random-glider [_]
    (clear-board nil)
    (game-logic/create-glider game))

  (reify
    om/IInitState
    (init-state [_]
      {:paused-label "Start"
       :started-label "Pause"
       :clear-label "Clear"
       :insert-glider "Random Glider"})

    om/IRenderState
    (render-state [_ state]
      (let [simulation-state (get game :simulation-state)
            attributes       #js {:onClick toggle-simulation}]
        (omdom/div nil
          (omdom/div nil
            (if simulation-state
              (omdom/button attributes (get state :started-label))
              (omdom/button attributes (get state :paused-label)))
            (omdom/button #js {:onClick clear-board} (get state :clear-label)))
          (omdom/div nil
            (omdom/button
             #js {:className "insert-glider" :onClick random-glider}
             (get state :insert-glider))))))))

(defn menu-component [app owner]
  (reify
    om/IRender
    (render [_]
      (let [header-label  (get-in app [:main-app :info :title])
            game          (get-in app [:main-app :game])]
        (omdom/div nil
         (omdom/h1 nil header-label)
         (om/build preview game)
         (om/build controls game))))))
