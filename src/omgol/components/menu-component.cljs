(ns omgol.components.menu-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn menu-component [app owner]
  (reify
    om/IRender
    (render [this]
      (let [header-label (get-in app [:main-app :info :title])]
        (omdom/h1 nil header-label)))))
