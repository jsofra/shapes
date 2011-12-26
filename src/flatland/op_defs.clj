(ns flatland.op-defs
  (:require [flatland.protocols :as proto]))

(defn proto-symbol [name] (symbol "flatland.protocols" (str name)))

(defmacro defcsg [name doc]
  `(defn ~name ~doc [& ~'shapes]
     (let [[~'f & ~'r] (apply flatland.core/convert-shapes ~'shapes)]
       (proto/->shape-data (~(proto-symbol name) ~'f ~'r)))))

(defmacro defgenericop [name doc return-fn & args]
  `(defn ~name ~doc [~'geometry ~@args]
     (~return-fn
      (~(proto-symbol name) (flatland.core/*geometry-converter* ~'geometry) ~@args))))

(defmacro defspatialcon [name doc & args]
  `(defgenericop ~name ~doc proto/->shape-data ~@args))

(defmacro defspatialrel [name doc & args]
  `(defn ~name ~doc [~'this ~'other ~@args]
     (~(proto-symbol name)
      (flatland.core/*geometry-converter* ~'this)
      (flatland.core/*geometry-converter* ~'other) ~@args)))

(defmacro defshape [name doc]
  `(defn ~name ~doc [~'coords] {:shape ~(keyword name) :coords ~'coords}))