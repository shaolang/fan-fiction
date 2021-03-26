# fan-fiction [![Clojars Project](https://img.shields.io/clojars/v/fan-fiction.svg)](https://clojars.org/fan-fiction)

Write [Storybook][storybook] in ClojureScript with ease.

__Library is still undergoing development, so expect frequent changes to
the API.__ The current version only supports [Reagent][reagent].

## Quick Start

`fan-fiction` does not include [Reagent][reagent] as its dependency, so
add both as the development dependency to your tool of choice.

`fan-fiction.reagent` contains two macros:

* `foreword`: creates the `default` property that [Storybook][storybook]
  expects.
* `defstory`: well, the story.

```clojure
(ns hello.world
  (:require [fan-fiction.reagent :refer [foreword defstory]]))

(defn greeting [person]   ;; this probably should be in a different namespace
  [:h1 "Hello, " person "!"])

(foreword :title          "Greeting component"
          :component      greeting
          :hide-controls  false)      ;; default is true

(defstory hello-world [greeting "World"])
```

`hide-controls` is an alias of the longer `hideNoControlsWarning` setting.
The equivalent [Component Story Format (CSF)][csf] in JavaScript is as follows:

```javascript
import React from 'react';
import { Greeting } from './greeting';

export default {
  title: 'Greeting component',
  component: Greeting,
  parameters: {controls: {hideNoControlsWarning: false}}
};

export const HelloWorld = () => <Greeting name='World' />;
```

### Reagent Form-2

`defstory` supports creating a Reagent Form-2 story, as shown below:

```clojure
(defstory hello
  [greeting "Hi, "
   person   (atom "world")]
  [:h1 greeting @person "!"])
```

That four-liner above expands to the following:

```clojure
(def ^:export hello
  (let [greeting  "Hi, "
        person    (atom "world")]
    (fn []
      [:h1 greeting @person "!"])))
```

### Arbitrary body

`defstory` also allows arbitrary body, as long as it ultimately returns
Reagent hiccup:

```clojure
(defstory hello
  [person {:greeting "Hi" :person "World"}]     ;; binding for form-2

  (let [{:keys [greeting person]} person]       ;; arbitrary body that return
    [:h1 greeting ", " person "!"]))            ;; Reagent hiccup
```

## License

Copyright © 2021 Shaolang Ai

Distributed under the MIT License

[storybook]: https://storybook.js.org
[reagent]: https://reagent-project.github.io
[csf]: https://storybook.js.org/docs/react/writing-stories/introduction#component-story-format
[args]: https://storybook.js.org/docs/react/writing-stories/args
