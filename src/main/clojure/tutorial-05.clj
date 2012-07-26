(ns tutorial-05
  (:use quil.core))

(def screen-w 640)
(def screen-h 480)

(def pyramid
  [[[0 1 0] [255 0 0]]
   [[-1 -1 1] [0 255 0]]
   [[1 -1 1] [0 0 255]]
   [[1 -1 -1] [0 255 0]]
   [[-1 -1 -1] [0 0 255]]])

(def cuboid
  [[1 1 -1]
   [-1 1 -1]
   [-1 1 1]
   [1 1 1]
   [1 -1 1]
   [-1 -1 1]
   [-1 -1 -1]
   [1 -1 -1]]
  )

(def r-pyramid (atom 0.0))
(def r-cuboid (atom 0.0))

(defn setup []
  (smooth)
  ; NOTA BENE: Need to do this to prevent the dreaded clipping plane problem;
  ;            see http://processing.org/discourse/beta/num_1203792825.html
  (frustum -2.66 2.66 -2 2 10 -10)
  (camera 0 0 8
          0 0 0
          0 1 0)
  )

(defn- init-scene []
  (background 0)
  (no-stroke)
  )

(defn- draw-pyramid []
  (translate -1.5 0 -6)
  (push-matrix)
  (rotate-y @r-pyramid)
  (begin-shape :triangles)
  (doseq [face [[0 1 2]
                [0 2 3]
                [0 3 4]
                [0 4 1]]]
    (doseq [face-vertex face]
      (apply fill (second (pyramid face-vertex)))
      (apply vertex (first (pyramid face-vertex)))
      )
    )
  (end-shape)
  (pop-matrix)
  )

(defn- draw-cuboid []
  (translate 3 0 0)
  (push-matrix)
  (rotate-x @r-cuboid)
  (rotate-y @r-cuboid)
  (rotate-z @r-cuboid)
  (begin-shape :quads)
  (doseq [[face face-color] [[[0 1 2 3] [0 255 0]]
                             [[4 5 6 7] [255 127 0]]
                             [[0 1 6 7] [255 0 0]]
                             [[4 5 2 3] [255 255 0]]
                             [[1 2 5 6] [0 0 255]]
                             [[0 3 4 7] [255 0 255]]]]
    (apply fill face-color)
    (doseq [face-vertex-num face]
      (apply vertex (cuboid face-vertex-num))
       )
     )
  (end-shape)
  (pop-matrix)
  )

(defn- update-angles-of-rotation []
  (swap! r-pyramid + 0.02)
  (swap! r-cuboid - 0.015)
  )

(defn draw []
  (init-scene)
  (draw-pyramid)
  (draw-cuboid)
  (update-angles-of-rotation)
  )

(defsketch main
  :title "NeHe tutorial 05"
  :setup setup
  :draw draw
  :size [screen-w screen-h]
  :renderer :opengl
  )