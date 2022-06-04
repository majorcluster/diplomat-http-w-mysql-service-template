(ns {{namespace}}.adapters.key-replacer-test
  (:require [clojure.test :refer :all]
            [{{namespace}}.adapters.key-replacer :refer :all]))

(deftest transform-keys-test
  (testing "converts snake cased str to kebab cased keyword"
    (is (= {:my-message "something" :payload {:my-name "Lenin"}}
           (transform-keys snake-str-to-kebab-key {"my_message" "something", "payload" {"my_name" "Lenin"}}))))
  (testing "converts kebab cased key to snake cased str"
    (is (= {"my_message" "something", "payload" {"my_name" "Lenin"}}
           (transform-keys kebab-key-to-snake-str {:my-message "something" :payload {:my-name "Lenin"}}))))
  (testing "converts snake cased key to kebab cased sr"
    (is (= {"my-message" "something", "payload" {"my-name" "Lenin"}}
           (transform-keys snake-key-to-kebab-str {"my_message" "something" :payload {"my_name" "Lenin"}}))))
  (testing "coverts snake cased str from vector to kebab cased keyword"
    (is (= [{:my-message "something" :payload {:my-name "Lenin"}}{:my-age 43}]
           (transform-keys snake-str-to-kebab-key [{"my_message" "something", "payload" {"my_name" "Lenin"}} {"my_age" 43}]))))
  (testing "converts kebab cased keys from vector to snake cased str"
    (is (= [{"my_message" "something", "payload" {"my_name" "Lenin"}}{"my_age" 43}]
           (transform-keys kebab-key-to-snake-str [{:my-message "something" :payload {:my-name "Lenin"}}{:my-age 43}]))))
  (testing "converts snaked keys in a list to kebab cased keys"
    (is (= '({:id 1, :name "croissant", :unit-grams 200, :price 5.40M})
           (transform-keys snake-key-to-kebab-key '({:id 1, :name "croissant", :unit_grams 200, :price 5.40M}))))))