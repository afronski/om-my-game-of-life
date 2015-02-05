(ns omgol.components.menu-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]
            [omgol.drawing :as drawing]))

(defn preview [game-settings owner]
  (reify
    om/IDidMount
    (did-mount [_]
      (let [cell-size (get game-settings :preview-cell-size)
            color     (get game-settings :alive-color)
            canvas    (om/get-node owner)]
        (drawing/fill-cell 1 1 cell-size color canvas)))

    om/IRender
    (render [_]
      (let [board-width       (get game-settings :board-width)
            board-height      (get game-settings :board-height)
            preview-cell-size (get game-settings :preview-cell-size)
            cell-size         (get game-settings :cell-size)
            scale             (/ cell-size preview-cell-size)
            preview-width     (/ board-width scale)
            preview-height    (/ board-height scale)]
        (omdom/canvas #js {:className "preview"
                           :width preview-width
                           :height preview-height})))))

(defn menu-component [app owner]
  (reify
    om/IRender
    (render [_]
      (let [header-label  (get-in app [:main-app :info :title])
            game-settings (get-in app [:main-app :game])
            ]
        (omdom/div nil
         (omdom/h1 nil header-label)
         (om/build preview game-settings))))))
