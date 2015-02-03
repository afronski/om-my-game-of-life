(ns omgol.components.board-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn board-component [app owner]
  (reify
    om/IRender
    (render [this]
      (omdom/canvas #js {:className "playground"}))))
