package com.kbdisplay.ls1710.web.view.dataJournal.component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * класс для обозначения и сравнение версий измерений/испытаний.
 *
 * версии представляют из себя стандартную запись версий типа 1.0 или 2.0.15.
 * класс позволяет сравнивать версии между собой, выяснять какая версия больше,а
 * какая меньше. также позволяет обращаться к конкретной части версии получая и
 * устанавливая значение версии.
 *
 * @author Gavrik
 *
 */
public class Version implements Comparable<Version> {

	/**
	 * номер версии строкой.
	 */
	private String version;
	/**
	 * составные части номера версии.
	 */
	private List<Integer> parts;


	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return version;
	}

	/**
	 * конструктор, создающий объект версии по строке типа 1.0 или 2.0.15.
	 *
	 * @param version
	 *            номер версии
	 */
	public Version(final String version) {
		if (version == null) {
			throw new IllegalArgumentException("Version can not be null");
		}
		if (!version.matches("[0-9]+(\\.[0-9]+)*")) {
			throw new IllegalArgumentException("Invalid version format");
		}
		String[] thisParts = version.split("\\.");
		parts = new ArrayList<Integer>();
		for (int i = 0; i < thisParts.length; i++) {
			parts.add(Integer.parseInt(thisParts[i]));
		}

		this.version = version;
	}

	/**
	 * получения количества частей в версии.
	 *
	 * например для версии 1.0 вернет 2, а для 2.0.15 вернет 3.
	 *
	 * @return количество частей в версии
	 */
	public int length() {
		return this.parts.size();
	}

	/**
	 * получение значения заданной части из номера версии.
	 *
	 * Части нумеруются начиная с 0. Если запрашиваемый номер больше количества
	 * частей то выбрасывается исключение, что превышен диапазон значений.
	 *
	 * например для версии 2.0.15 .getPart(0) = 2
	 * 							  .getPart(1) = 0
	 * 							  .getPart(2) = 15
	 *
	 * @param partNumber
	 *            номер части версии
	 * @return значения заданной части версии
	 */
	public int getPart(final int partNumber) {
		if (partNumber < this.length()) {
			return parts.get(partNumber).intValue();
		} else {
			throw new IllegalArgumentException("Out of range value of parts");
		}
	}

	/**
	 * устанавливает значение заданной части номера версии.
	 *
	 * если заданный номер части версии больше, чем частей в текущей версии, то
	 * добавляется новая часть.
	 *
	 * @param partNumber
	 *            номер части
	 * @param value
	 *            новое значение
	 */
	public void setPart(final int partNumber, final int value) {
		if (partNumber < this.length()) {
			parts.set(partNumber, Integer.valueOf(value));
		} else {
			parts.add(value);
		}

		ListIterator<Integer> iterrator = parts.listIterator();
		String newVersion = "";

		while (iterrator.hasNext()) {
			newVersion += iterrator.next();
			if (iterrator.hasNext()) {
				newVersion += ".";
			}
		}
		this.version = newVersion;
	}

	@Override
	public int compareTo(final Version that) {
		if (that == null) {
			return 1;
		}
		int length = Math.max(this.length(), that.length());
		for (int i = 0; i < length; i++) {
			int thisPart = 0;
			if (i < this.length()) {
				thisPart = this.getPart(i);
			}
			int thatPart = 0;
			if (i < that.length()) {
				thatPart = that.getPart(i);
			}
			if (thisPart < thatPart) {
				return -1;
			}
			if (thisPart > thatPart) {
				return 1;
			}
		}
		return 0;
	}

}
