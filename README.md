# flatland

flatland is a geometry library for Clojure.

flatland provides an abstract API for geometry operations and has a default implementation building on [clj-jts] (https://github.com/jsofra/clj-jts).

flatland allows for representation of geometry as Clojure data and a number of operations on that data.

The supported operations include Constructive Solid Geometry operations, spatial analysis and relation operations, unary constructive operations and measurement operations.


flatland provides similar functionality to [The Java Topology Suite] (http://www.vividsolutions.com/jts/main.htm) and the Python project [shapely] (http://gispython.org/shapely/manual.html).

## Usage

Construction functions are provided for the shapes:
point, line-string, linear-ring, polygon, multi-point, multi-line-string, multi-polygon and geometry-collection

Each of the construction functions are only provided as shortcuts for contructin shape maps.
A shape map is a map with the keys :shape and :coords and the construction functions all build a map with the given shape type and given coords, e.g.

```clojure
user> (point {:y 1 :x 2})
{:shape :point :coords {:y 1 :x 2}}
```
### Constructive Solid Geometry
flatland supports a set of Constructive Solid Geometry operations:
union, difference, sym-difference and intersection

```clojure
user> (union (point {:x 1 :y 2}) (point {:x 2 :y 4}))
{:shape :multi-point, :coords [{:x 1.0, :y 2.0} {:x 2.0, :y 4.0}]}
```

More documentaion to come!
