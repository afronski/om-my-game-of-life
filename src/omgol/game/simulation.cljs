(ns omgol.game.simulation
  (:require [omgol.game.rules :as rules]))

(defn bootstrap [state]
  (defn tick []
    (rules/single-step state))

  (defn start-simulation []
    (let [interval (get-in @state [:main-app :game :interval])]
      (swap! state assoc-in [:main-app :game :simulation-id]
        (js/setInterval tick interval))))

  (defn stop-simulation []
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
          (stop-simulation))))))
