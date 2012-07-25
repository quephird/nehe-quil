(ns tutorial-03
  (:use quil.core))

(def screen-w 640)
(def screen-h 480)

(defn setup []
  (smooth)
  (background 0)
  ; NOTA BENE: Need to do this to prevent the dreaded clipping plane problem;
  ;            see http://processing.org/discourse/beta/num_1203792825.html
  (frustum -2.66 2.66 -2 2 10 -10)
  (camera 0 0 5
          0 0 0
          0 1 0)
  )

(defn draw []
  (translate -1.5 0 -6)
  (begin-shape :triangles)
  (fill 255 0 0)
  (vertex 0 1 0)
  (fill 0 255 0)
  (vertex -1 -1 0)
  (fill 0 0 255)
  (vertex 1 -1 0)
  (end-shape)

  (translate 3 0 0)
  (fill 127 127 255)
  (begin-shape :quads)
  (vertex -1 1 0)
  (vertex 1 1 0)
  (vertex 1 -1 0)
  (vertex -1 -1 0)
  (end-shape)
  )

(defsketch main
  :title "NeHe tutorial 03"
  :setup setup
  :draw draw
  :size [screen-w screen-h]
  :renderer :opengl
  )
