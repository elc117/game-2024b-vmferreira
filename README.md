# Jardim Botânico Quest

Jogo de Quiz *Point And Click*, graficamente inspirado por King's Quest.

## Diagrama de Classes:
```
                                           ┌────────┐                         
                                           │ Entity │                         
                                           └▲──────▲┘                         
                                            │      │                          
                              ┌─────────────┴┐ ┌───┴────────────┐             
 ┌───────────────────────────x┤ PlayerEntity │x┤ InteractEntity │             
 │                            └──────────────┘│└▲───────▲─────▲─┘             
 │                                            │ │       │     └──┐            
 │                               ┌────────────┘ │       │        │            
 │                               │              │       │        │            
 │                               │              │       │        │            
 │                               │              │       │        │            
┌┴─────┐     ┌──────┐            │  ┌───────────┼┐┌─────┴──────┐┌┼───────────┐
│ Main ├────x┤ Room ├────────────┘  │ InfoEntity ││ QuizEntity ││ MoveEntity │
└┬─────┘     └──────┘               └────────────┘└─┬──────────┘└────────────┘
 │                                                  │                         
 │                                                  │                         
 │                                                  │                         
 │                                                  │                         
 │                                                  │                         
 │                                                  │                         
 │                                                  │                         
 │  ┌─────────────┐                ┌──────┐         │                         
 └─x┤ DialogueBox ├───────────────x┤ Quiz ├x────────┘                         
    └─────────────┘                └──────┘
```
