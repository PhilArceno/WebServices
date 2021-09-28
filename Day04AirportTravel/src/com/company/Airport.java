package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Airport {
    private String code, city;
    private double latitude, longitude;
    private Date openingDate;
    enum AirportType {
        Passenger, Cargo;
    }
    private AirportType Type;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
    private SimpleDateFormat formatterFile = new SimpleDateFormat("yyyy-MM-dd");

    public Airport(String code, String city, double latitude, double longitude, Date openingDate, String type) throws ParameterInvalidException {
        setCode(code);
        setCity(city);
        setLatitude(latitude);
        setLongitude(longitude);
        setOpeningDate(openingDate);
        setType(type);
    }

    public Airport(String dataLine) throws ParameterInvalidException {
        String[] data = dataLine.split(";");
        if(data.length != 6) {
            throw new ParameterInvalidException("Invalid number of data inputted.");
        }
        String[] date = data[4].split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

        setCode(data[0]);
        setCity(data[1]);
        setLatitude(Double.parseDouble(data[2]));
        setLongitude(Double.parseDouble(data[3]));
        setOpeningDate(cal.getTime());
        setType(data[5]);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws ParameterInvalidException {
        if (!code.matches("[A-Z]{3}")) {
            throw new ParameterInvalidException("Airport code does not meet the correct format.");
        }
            this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws ParameterInvalidException {
        if (city.isEmpty()) {
            throw new ParameterInvalidException("City string is empty.");
        }
        if (city.contains(";")) {
            throw new ParameterInvalidException("City contains semi-colon.");
        }
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) throws ParameterInvalidException {
        if (!(latitude >= -90 && latitude <= 90)) {
            throw new ParameterInvalidException("Latitude is not within the valid range.");
        }
            this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws ParameterInvalidException {
        if (!(longitude >= -180 && longitude <= 80)) {
            throw new ParameterInvalidException("Longitude is not within the valid range.");
        }
            this.longitude = longitude;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) throws ParameterInvalidException {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.YEAR, 1800);
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.MONTH, 12);
        c2.set(Calendar.DATE, 31);
        c2.set(Calendar.YEAR, 2100);
        if (!(openingDate.after(c.getTime()) && openingDate.before(c2.getTime()))) {
            throw new ParameterInvalidException("Date is not between the years 1800 to 2100");
        }
        this.openingDate = openingDate;
    }

    public AirportType getType() {
        return Type;
    }

    public void setType(String type) throws ParameterInvalidException {
        if (type.equals(AirportType.Passenger.toString())) {
            this.Type = AirportType.Passenger;
        } else if (type.equals(AirportType.Cargo.toString())) {
            this.Type = AirportType.Cargo;
        } else {
            throw new ParameterInvalidException("Airport type is not Passenger or Cargo");
        }
    }

    public String toDataLine() {
        return String.format("%s;%s;%.7f;%.7f;%s;%s",
                this.code,
                this.city,
                this.latitude,
                this.longitude,
                formatterFile.format(this.openingDate),
                this.Type
                );
    }

    @Override
    public String toString() {
        return this.code + " airport is in " + this.city + " at latitude " + this.latitude + " and longitude " + this.longitude
                + ", opened " + formatter.format(this.openingDate);
    }
}
