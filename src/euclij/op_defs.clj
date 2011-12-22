(ns euclij.op-defs
  (:require [euclij.protocols :as proto]))

(defn proto-symbol [name] (symbol "euclij.protocols" (str name)))

(defmacro defcsg [name doc]
  `(defn ~name ~doc [& ~'shapes]
     (let [[~'f & ~'r] (apply euclij.core/convert-shapes ~'shapes)]
       (proto/->shape-data (~(proto-symbol name) ~'f ~'r)))))

(defmacro defgenericop [name doc return-fn & args]
  `(defn ~name ~doc [~'geometry ~@args]
     (~return-fn
      (~(proto-symbol name) (euclij.core/*geometry-converter* ~'geometry) ~@args))))

(defmacro defspatialcon [name doc & args]
  `(defgenericop ~name ~doc proto/->shape-data ~@args))

(defmacro defspatialrel [name doc & args]
  `(defn ~name ~doc [~'this ~'other ~@args]
     (~(proto-symbol name)
      (euclij.core/*geometry-converter* ~'this)
      (euclij.core/*geometry-converter* ~'other) ~@args)))

(defmacro defshape [name doc]
  `(defn ~name ~doc [~'coords] {:shape ~(keyword name) :coords ~'coords}))