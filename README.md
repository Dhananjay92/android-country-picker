# Country Picker for Android

<b>Start country picker</b>


    Intent intent = new Intent(this, CountryCodePickerActivity.class);
    startActivityForResult(intent, CountryCodePickerUtil.REQUEST_CODE_COUNTRY_CODE_PICKER);




<b>Get the selected country</b>


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

<b>Sample Images</b>

<div>
    <img src="https://github.com/PavanKumarPatruni/android-country-picker/blob/master/Screenshot_20180817-053024.jpg">
    <img src="https://github.com/PavanKumarPatruni/android-country-picker/blob/master/Screenshot_20180817-053100.jpg">
</div>

<h3>Author</h3>

<b>Pavan Kumar Patruni</b>
