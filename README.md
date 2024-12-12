# Jardim Botânico Quest

Jogo de Quiz *Point And Click*, graficamente inspirado por King's Quest.

## Diagrama de Classes:
```
Linha que acaba em uma seta: Indica herança
Linha que acaba em um X: Indica composição
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
