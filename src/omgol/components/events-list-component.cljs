(ns omgol.components.events-list-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn historical-item [item owner]
  (reify
    om/IRender
    (render [_]
      (omdom/li #js {:className "historical-item"} (:text item)))))

(defn events-list-component [app owner]
  (defn undo [_]
    (.info js/console "Undo!"))

  (defn redo [_]
    (.info js/console "Redo!"))

  (reify
    om/IInitState
    (init-state [_]
      {:history [{:text "Test #1"}
                 {:text "Test #2"}]})

    om/IRenderState
    (render-state [_ state]
      (let [events-label     (get-in app [:main-app :events :label])
            simulation-state (get-in app [:main-app :game :simulation-state])]
        (omdom/div nil
          (omdom/header nil
            (omdom/h1 nil events-label)
            (omdom/div #js {:id "events-controls"}
              (omdom/button #js {:onClick undo :disabled simulation-state} "Undo")
              (omdom/button #js {:onClick redo :disabled simulation-state} "Redo")))
          (apply omdom/ul #js {:className "events-list"}
            (om/build-all historical-item (:history state))))))))
