# Meridian Shapes

Meridian Shapes is a geometry library for Clojure.

Shapes provides an abstract API for geometry operations and has a default implementation building on [clj-jts] (https://github.com/jsofra/clj-jts).

Shapes allows for representation of geometry as Clojure data and a number of operations on that data.

The supported operations include Constructive Solid Geometry operations, spatial analysis and relation operations, unary constructive operations and measurement operations.


Shapes provides similar functionality to [The Java Topology Suite] (http://www.vividsolutions.com/jts/main.htm) and the Python project [shapely] (http://gispython.org/shapely/manual.html).

## Usage

Shapes has a base representation for geometry. The geometry are represented by standard Clojure datastructures (maps or records). These geometry structures map directly to the structures used in the [GeoJSON] (http://www.geojson.org/) specification. This conformance to the GeoJSON specification means that Shapes gets standardisation for free.


Construction functions are provided for the shapes:
point, line-string, linear-ring, polygon, multi-point, multi-line-string, multi-polygon and geometry-collection

The construction functions create records with the keys, `:type` and `:coordinates`

```clojure
user> (require '[meridian.shapes :as ms])
nil
user> (ms/point [1 2])
#meridian.shapes.Point{:type :Point, :coordinates [1 2]}
```

### Geometry Abstractions

Shapes defines a rich set of common abstractions:
ConstructiveSolidGeometry, SpatialConstruction, SpatialRelations, Measurable, Locatable and SimplicityTest

Shapes does not come with a default implementation of these abstractions. You can however use the default provided by Meridian [Shapes-impl] (http://github.com/jsofra/shapes-impl).

#### Constructive Solid Geometry
The ConstructiveSolidGeometry abstraction provides a set of operations:
union, difference, sym-difference and intersection

```clojure
user> (use '[meridian.shapes.impl record map])
nil
user> (ms/union (ms/point [1 2]) (ms/point [2 4]))
#meridian.shapes.MultiPoint{:type :multi-point, :coordinates [[1.0, 2.0] [2.0, 4.0]]}
```

More documentaion to come!

## License

Copyright © James Sofra, 2013

Distributed under the Eclipse Public License, the same as Clojure.
