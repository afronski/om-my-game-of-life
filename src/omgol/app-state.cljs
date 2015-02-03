(ns omgol.app-state)

(def app-state
  (atom
   {:main-app
    {:info
     {:title "Game of Life"
      :footer "cljs + om = ♥"
      :copyright "afronski (Wojtek Gawroński) - 2015"
     }
     :events
     {:label "Events"
     }
    }
   }))
