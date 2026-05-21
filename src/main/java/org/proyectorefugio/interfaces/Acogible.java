package org.proyectorefugio.interfaces;

/**
 * Interfaz que deben implementar los animales susceptibles de ser acogidos
 * temporalmente por una persona fuera del refugio.
 * Si un Perro es agresivo, no puede estar en regimen de Acogida, solo
 * en Adopción
 */
public interface Acogible {
    boolean puedeSerAcogido();
}
