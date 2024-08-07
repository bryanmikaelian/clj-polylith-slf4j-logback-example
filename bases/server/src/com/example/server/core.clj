(ns com.example.server.core
  (:gen-class)
  (:require
    [io.pedestal.log :as l]))


(defn start
  []

  (l/info :msg "hello. I show up in production.")
  (l/error :msg "hello. I show up in production.")
  (l/warn :msg "hello. I show up in production.")
  (l/debug :msg "hello. I show up in development."))


(defn -main
  [& args]

  (start))
