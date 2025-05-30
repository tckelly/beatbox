# 🥁 BeatBox

**BeatBox** is a Java-based desktop MIDI sequencer that lets users create and play simple beats using a visual
interface. Built with Java Swing and the javax.sound.midi package, this project demonstrates core concepts in
object-oriented programming, GUI development, and MIDI event handling.

This project was inspired by a tutorial in the Head First Java book.

---

## Features

- Visual grid to toggle beats for 16 different instruments
- Start/stop playback with real-time audio output
- Adjustable tempo, meter and looping playback
- Save and load beats to/from disk
- Uses standard MIDI percussion instruments

---

## How to Run

### Prerequisites
- Java 8 or higher installed

### Run It
```bash
git clone https://github.com/tckelly/beatbox.git

cd beatbox

./mvnw compile exec:java