(ns tutorial-06
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 480)

(def vertices
  [[-1 -1 1]
  [1 -1 1]
  [1 1 1]
  [-1 1 1]
  [1 -1 -1]
  [-1 -1 -1]
  [-1 1 -1]
  [1 1 -1]])

(def img (atom nil))
(def rot-x (atom 0.0))
(def rot-y (atom 0.0))
(def rot-z (atom 0.0))

(defn setup []
  (reset! img (load-image "src/main/resources/NeHe.png"))
  (texture-mode :normalized)
  (smooth)
  (no-stroke)
  ; NOTA BENE: Need to do this to prevent the dreaded clipping plane problem;
  ;            see http://processing.org/discourse/beta/num_1203792825.html
  (frustum -2.66 2.66 -2 2 10 -10)
  (camera 0 0 8
          0 0 0
          0 1 0)
  )

(defn- draw-textured-cube []
  (translate 0 0 -5)
  (push-matrix)
  (rotate-x @rot-x)
  (rotate-y @rot-y)
  (rotate-z @rot-z)
  (begin-shape :quads)
  (texture @img)

  (doseq [face [[0 1 2 3]
                [4 5 6 7]
                [3 2 7 6]
                [5 4 1 0]
                [1 4 7 2]
                [5 0 3 6]]]
    (doseq [v (map #(concat %1 %2) (map #(vertices %) face) [[0 0][1 0][1 1][0 1]])]
      (apply vertex v)))

  (end-shape)
  (pop-matrix)
  )

(defn- update-angles-of-rotation []
  (swap! rot-x + 0.03)
  (swap! rot-y + 0.02)
  (swap! rot-z + 0.04)
  )

(defn draw []
  (background 0)
  (draw-textured-cube)
  (update-angles-of-rotation)
  )

(defsketch main
  :title "NeHe tutorial 06"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl
  )
