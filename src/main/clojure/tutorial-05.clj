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
  (background 0)
  ; NOTA BENE: Need to do this to prevent the dreaded clipping plane problem;
  ;            see http://processing.org/discourse/beta/num_1203792825.html
  (frustum -2.66 2.66 -2 2 10 -10)
  (camera 0 0 8
          0 0 0
          0 1 0)
  )

(defn draw []
  (background 0)
  (no-stroke)

  (translate -1.5 0 -6)
;  (push-matrix)
;  (rotate-y @r-triangle)
;  (begin-shape :triangles)
;  (doseq [face [[0 1 2]
;                [0 2 3]
;                [0 3 4]
;                [0 4 1]]]
;    (doseq [[face-vertex face-color] face]
;      (apply fill (pyramid face-color))
;      (apply vertex (pyramid face-vertex))
;      )
;    )
;  (end-shape)
;  (pop-matrix)

  (translate 3 0 0)
  (push-matrix)
  (rotate-x @r-cuboid)
  (begin-shape :quads)
  (doseq [[face face-color] [[[0 1 2 3] [0 255 0]]
                             [[4 5 6 7] [255 127 0]]
                             [[3 2 7 6] [255 0 0]]
                             [[5 4 1 0] [255 255 0]]
                             [[1 4 7 2] [0 0 255]]
                             [[5 0 3 6] [255 0 255]]]]
    (apply fill face-color)
    (doseq [face-vertex-num face]
      (apply vertex (cuboid face-vertex-num))
       )
     )
  (end-shape)
  (pop-matrix)

  (swap! r-pyramid + 0.02)
  (swap! r-cuboid - 0.15)
  )

(defsketch main
  :title "NeHe tutorial 05"
  :setup setup
  :draw draw
  :size [screen-w screen-h]
  :renderer :opengl
  )