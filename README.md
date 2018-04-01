# android-country-picker

Intent intent = new Intent(this, CountryCodePickerActivity.class);
startActivityForResult(intent, CountryCodePickerUtil.REQUEST_CODE_COUNTRY_CODE_PICKER);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == CountryCodePickerUtil.REQUEST_CODE_COUNTRY_CODE_PICKER && data.hasExtra(CountryCodePickerUtil.KEY_SELECTED_COUNTRY)) {

                selectedCountry = (Country) data.getSerializableExtra(CountryCodePickerUtil.KEY_SELECTED_COUNTRY);
                textViewCountryCode.setText(selectedCountry.getDialCode());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
