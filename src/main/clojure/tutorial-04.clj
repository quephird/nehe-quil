(ns tutorial-04
  (:use quil.core))

(def screen-w 640)
(def screen-h 480)

(def r-triangle (atom 0.0))
(def r-quad (atom 0.0))

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
  (background 0)
  (translate -1.5 0 -6)
  (push-matrix)
  (rotate-y @r-triangle)
  (begin-shape :triangles)
  (fill 255 0 0)
  (vertex 0 1 0)
  (fill 0 255 0)
  (vertex -1 -1 0)
  (fill 0 0 255)
  (vertex 1 -1 0)
  (end-shape)
  (pop-matrix)

  (translate 3 0 0)
  (push-matrix)
  (rotate-x @r-quad)
  (fill 127 127 255)
  (begin-shape :quads)
  (vertex -1 1 0)
  (vertex 1 1 0)
  (vertex 1 -1 0)
  (vertex -1 -1 0)
  (end-shape)
  (pop-matrix)

  (swap! r-triangle + 0.2)
  (swap! r-quad - 0.15)
  )

(defsketch main
  :title "NeHe tutorial 04"
  :setup setup
  :draw draw
  :size [screen-w screen-h]
  :renderer :opengl
  )
