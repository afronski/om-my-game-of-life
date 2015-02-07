(ns omgol.app-state)

(def app-state
  (atom
   {:main-app
    {:info
     {:title "Game of Life"
      :footer "cljs + om = ♥"
      :copyright "afronski (Wojtek Gawroński) - 2015"
     }
     :game
     {:board-width 500
      :board-height 500
      :preview-cell-size 2
      :cell-size 10
      :alive-color "rgb(0, 0, 0)"
      :dead-color "rgb(255, 255, 255)"
      :grid-color "rgb(50, 50, 50)"
      :simulation-state false
      :simulation-id nil
      :interval 1000
      :alive-cells [ [10 10] ]
     }
     :events
     {:label "Events"
     }
    }
   }))
