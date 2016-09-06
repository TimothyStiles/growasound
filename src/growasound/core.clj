(ns growasound.core
  (:use [funimage imp])
  (:require 
   [overtone.live :as o]
   [overtone.inst.piano :as p]
   [overtone.studio.mixer :as m])
  (:gen-class))

;; Introduction

; Hey igem peoples and others. This is just a neat little proof of concept that turns an image into a sound.

; "test-image" is a 96 X 96 image of a pokemon that I mutated with another project.

; "get-pixels" is a simple function that maps from the image plus data structure to compressed 
; rgb values.

; "quick song" takes test-image as a compressed RGB list and maps then to notes and rhythm and 
; then plays the song.

; To play the test just run lein repl inside the growasound direcotry and listen!
;
; -Tim

(def test-image
  (open-imp "resources/poke.png"))

(defn get-pixels
  "Takes an image and creates numerical values corresponding to pixels."
  [imp]
  (let [width (range 0 (get-width imp))
        height (range 0 (get-height imp))]
    (for [h height w width]
      (get-pixel imp h w))))

#_(defn quick-sound
  "Takes values from get-pixels and makes a little sound."
  [pixels]
  (let [filtered-pix (remove #(= % (first pixels)) pixels)
        pos-pix (map #(if (pos? %) % (unchecked-negate %)) filtered-pix)
        notes (map #(rem % 48) pos-pix)
        rhythm (reverse (map #(rem % 500) pos-pix))]
    (do (show-imp (copy-imp test-image))
        (m/recording-start "poke-scream.wav")
        (doall (map #(o/at (+ (o/now) %1) (p/piano %2)) rhythm notes))
        (Thread/sleep 10000)
        (m/recording-stop))))

(defn song
  "takes image. Makes song."
  [pixels]
  (let [notes "pi"]
    p))
  (defn -main 
  "For playing out in BASH"
  [&args]
  (quick-sound (get-pixels test-image)))
