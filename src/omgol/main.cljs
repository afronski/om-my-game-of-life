(ns omgol.main
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [omgol.app-state :as app]
            [omgol.components.menu-component :as menu]
            [omgol.components.board-component :as board]
            [omgol.components.events-list-component :as events]
            [omgol.components.footer-component :as footer]
            [omgol.game.simulation :as simulation]))

; Interop with JS examples ;).

(enable-console-print!)

(.log js/console "Welcome to Game of Life!")
(.log js/console "cljs + om = ♥")

; Bootstrapping simulation logic.
(simulation/bootstrap app/app-state)

; Rooting multiple components.

(om/root
 menu/menu-component
 app/app-state
 {:target (. js/document (getElementById "menu"))})

(om/root
 board/board-component
 app/app-state
 {:target (. js/document (getElementById "board"))})

(om/root
 events/events-list-component
 app/app-state
 {:target (. js/document (getElementById "events"))})

(om/root
 footer/footer-component
 app/app-state
 {:target (. js/document (getElementById "footer"))})
