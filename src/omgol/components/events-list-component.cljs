(ns omgol.components.events-list-component
  (:require [om.core :as om :include-macros true]
            [om.dom :as omdom :include-macros true]))

(defn historical-item [item owner]
  (reify
    om/IRender
    (render [_]
      (let [active  (= (:idx item) (:active item))
            classes (if active
                      "historical-item active-historical-item"
                      "historical-item")]
       (omdom/li
        #js {:className classes}
        (str "Generation " (:idx item) ", alive: " (count (:state item))))))))

(defn events-list-component [app owner]
  (defn restore-state [history idx]
    (om/update! app [:main-app :game :alive-cells] (get history idx)))

  (defn undo [_]
    (let [active-state (get-in app [:main-app :game :active-state])
          history      (get-in app [:main-app :game :history])
          new-state    (if (= active-state nil)
                         (- (count history) 2)
                         (- active-state 1))]
      (when (and (not= active-state 0)
                 (> (count history) 1))
        (om/update! app [:main-app :game :active-state] new-state)
        (restore-state history new-state))))

  (defn redo [_]
    (let [active-state (get-in app [:main-app :game :active-state])
          history      (get-in app [:main-app :game :history])
          new-state    (if (= active-state (- (count history) 1))
                         nil
                         (+ active-state 1))]
      (when (and (not= active-state (- (count history) 1))
                 (not= active-state nil)
                 (> (count history) 1))
        (om/update! app [:main-app :game :active-state] new-state)
        (restore-state history new-state))))

  (reify
    om/IRender
    (render [_]
      (let [events-label     (get-in app [:main-app :events :label])
            simulation-state (get-in app [:main-app :game :simulation-state])
            history          (get-in app [:main-app :game :history])

            active-state     (get-in app [:main-app :game :active-state])
            active           (if (= active-state nil)
                               (- (count history) 1)
                               active-state)

            prepare-history  (fn [idx item] {:idx idx :state item :active active})]
        (omdom/div nil
          (omdom/header nil
            (omdom/h1 nil events-label)
            (omdom/div #js {:id "events-controls"}
              (omdom/button
               #js {:onClick undo :disabled simulation-state}
               "Undo")
              (omdom/button
               #js {:onClick redo :disabled simulation-state}
               "Redo")))
          (apply omdom/ul #js {:className "events-list"}
            (om/build-all
             historical-item
             (map-indexed prepare-history history))))))))
