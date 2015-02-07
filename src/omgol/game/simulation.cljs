(ns omgol.game.simulation
  (:require [om.core :as om :include-macros true]))

(defn bootstrap [state]
  (defn tick []
    (.warn js/console "Tick!"))

  (defn start-simulation []
    (.info js/console "Start!")
    (let [interval (get-in @state [:main-app :game :interval])]
      (.log js/console state interval)
      (swap! state assoc-in [:main-app :game :simulation-id]
        (js/setInterval tick interval))))

  (defn stop-simulation []
    (.info js/console "Stop!")
    (let [id (get-in @state [:main-app :game :simulation-id])]
      (js/clearInterval id)
      (swap! state assoc-in [:main-app :game :simulation-id] nil)))

  (add-watch state :simulation
    (fn [_ _ _ new-state]
      (let [started (get-in new-state [:main-app :game :simulation-state])
            id      (get-in new-state [:main-app :game :simulation-id])]
        (when (and (= true started) (= nil id))
          (start-simulation))
        (when (and (= false started) (not= nil id))
          (stop-simulation))
        ))))
