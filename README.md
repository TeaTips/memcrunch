MemCrunch
=========

Aim
---

MemCrunch is an off-heap data structure library that is designed with Mechanical Sympathy is mind. This means where
necessary some design best-practices are deliberately broken for the sake of performance.

Some sympathetic design principles include avoid unnecessary garbage creation, avoid boxing, use primitive types,
consider memory alignment, consider impact on CPU cache lines, use efficient algorithms, avoid blocking where possible,
use less instructions, and others.

TODO link to blog and forums.

Features
--------

* Off-heap memory management in three variants: NIO direct byte buffers, memory mapped files, and "unsafe" direct memory.
* Memory chunk pooling/reuse.
* Column-oriented storage provides advantages for both storage of big data sets, and for processing.

TODO more features!

