(defproject nehe-quil "0.1.0-SNAPSHOT"

  :description "Clojure implementations of the NeHe OpenGL tutorials"

  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [quil "1.3.0"]
                 [net.java.dev.jogl/jogl "1.1.1a"]]
  :native-dependencies [[net.java.dev.jogl/jogl-windows-i586 "2.4.2"]]
  :source-path "src/main/clojure/"
  :jvm-opts ["-Xmx1g" "-server"])
