(ns hiccup.core
  "Library for rendering a tree of vectors into a string of HTML.
  Pre-compiles where possible for performance."
  (:require [hiccup.compiler :as compiler]
            [hiccup.util :as util]))

(defmacro html
  "Render Clojure data structures to a string of HTML."
  [options & content]
  (if-let [mode (and (map? options) (:mode options))]
    (binding [compiler/*html-mode* mode]
      `(binding [compiler/*html-mode* ~mode]
         ~(apply compiler/compile-html content)))
    (apply compiler/compile-html options content)))

(def ^{:doc "Alias for hiccup.util/escape-html"}
  h util/escape-html)
