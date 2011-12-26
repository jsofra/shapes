(ns euclij.jts-impl
  (:refer-clojure :exclude [contains?])
  (:import [com.vividsolutions.jts.geom Geometry])
  (:require [euclij.protocols :as proto]
            [clj-jts.core :as jts]))

(extend-type Geometry
  proto/ConstructiveSolidGeometry
  (union [this others] (reduce #(.union %1 %2) (cons this others)))
  (difference [this others] (reduce #(.difference %1 %2) (cons this others)))
  (sym-difference [this others] (reduce #(.symDifference %1 %2) (cons this others)))
  (intersection [this others] (reduce #(.intersection %1 %2) (cons this others)))

  proto/SpatialConstruction
  (boundary [geometry] (.boundary geometry))
  (buffer [geometry distance] (.buffer geometry distance))
  (convex-hull [geometry] (.convexHull geometry))
  (bounding-box [geometry] (.getEnvelope geometry))

  proto/SpatialRelations
  (intersects? [this other] (.intersects this other))
  (disjoint? [this other] (.disjoint this other))
  (contains? [this other] (.contains this other))
  (covers? [this other] (.covers this other))
  (covered-by? [this other] (.coveredBy this other))
  (crosses? [this other] (.crosses this other))
  (overlaps? [this other] (.overlaps this other))
  (relate? [this other] (.relate this other))
  (within-distance? [this other distance] (.isWithinDistance this other distance))
  (distance [this other] (.distance this other))

  proto/Measurable
  (area [geometry] (.getArea geometry))
  (length [geometry] (.getLength geometry))
  
  proto/Locatable 
  (centroid [geometry] (.getCentroid geometry))
  (interior-point [geometry] (.getInteriorPoint geometry))
  (envelope [geometry] (.getEnvelopeInternal geometry))
  
  proto/SimplicityTest
  (simple? [geometry] (.isSimple geometry))
  
  proto/GeometryConversions
  (->shape-data [geom] (jts/->shape-data geom)))