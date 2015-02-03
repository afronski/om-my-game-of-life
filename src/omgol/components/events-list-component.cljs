(ns omgol.components.events-list-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn events-list-component [app owner]
  (reify
    om/IRender
    (render [this]
      (let [events-label (get-in app [:main-app :events :label])]
        (omdom/div nil
          (omdom/header nil
            (omdom/h1 nil events-label)
            (omdom/div #js {:id "events-controls"} nil))
          (omdom/ul #js {:className "events-list"} nil))))))
