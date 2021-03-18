# fan-fiction

Write [Storybook][storybook] in ClojureScript with ease.

__Library is still undergoing development, so expect frequent changes to
the API.__ The current version only supports [Reagent][reagent].

## Quick Start

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

The equivalent [Component Story Format (CSF)][csf] in JavaScript is as follows:

```javascipt
import React from 'react';
import { Greeting } from './greeting';

export default {
  title: 'Greeting component',
  component: Greeting
};

export const HelloWorld = () => <Greeting name='World' />;
```

## License

Copyright © 2021 Shaolang Ai

Distributed under the MIT License

[storybook]: https://storybook.js.org
[reagent]: https://reagent-project.github.io
[csf]: https://storybook.js.org/docs/react/writing-stories/introduction#component-story-format
