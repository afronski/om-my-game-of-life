(ns omgol.components.footer-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn footer-component [app owner]
  (reify
    om/IRender
    (render [this]
      (let [copyright (get-in app [:main-app :info :copyright])
            footer    (get-in app [:main-app :info :footer])]
        (omdom/div nil
          (omdom/p nil (omdom/small nil copyright))
          (omdom/p nil (omdom/small nil footer)))))))
