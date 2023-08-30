import React, { useState } from 'react';
import Cards from 'react-credit-cards-2';
import 'react-credit-cards-2/dist/es/styles-compiled.css';
import './PaymentForm.css';
import axios from "axios";

function PaymentForm() {
  const [state, setState] = useState({
    number: '',
    expiry: '',
    cvc: '',
    firstName: '',
    lastName: '',
    focus: '',
  });
  const [isValid, setIsValid]=useState(null);

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;
    setState((prev) => ({ ...prev, [name]: value }));
  }
  const handleCCInputChange = (evt) => {
    setIsValid("neutral");
    const { name, value } = evt.target;
    setState((prev) => ({ ...prev, [name]: value }));
  }

  const handleInputFocus = (evt) => {
    setState((prev) => ({ ...prev, focus: evt.target.name }));
  }
  const handleSubmit = (e) => {
    e.preventDefault();
    const API_URL = 'http://localhost:8080/v1/cards';
    const postData = {
      card_number:state.number,
      first_name:state.firstName,
      last_name:state.lastName,
      expiration:`20${state.expiry}`, //converting to YYYYMM 
      cvc:state.cvc
    }
    axios.post(API_URL, postData)
    .then(response => {
      console.log('post succesfuly');
      console.log(response);
    })
    .catch(error => {
      console.error('An error occurred:', error);
    });

  }

  const handleValidate= (e) => {
    e.preventDefault();
    // Replace 'API_URL' with the actual URL of the REST API you want to call
    const API_URL = 'http://localhost:8080/v1/cards/validate/'+state.number;
    axios.get(API_URL)
    .then(response => {
      if(response.data == true){
        setIsValid(true);
      }else{setIsValid(false)}
    })
    .catch(error => {
      console.error('An error occurred:', error);
    });
  }


  return (
    <div className="payment-form-container">
      <div className="credit-card-container">
        <Cards
          number={state.number}
          expiry={state.expiry}
          cvc={state.cvc}
          name={state.firstName + " " + state.lastName}
          focused={state.focus}
        />
      </div>
      <form className='form'>
        <div className="credit-card-field">
          <input
            color=""
            type="tel"
            name="number"
            className="credit-card-input"
            placeholder="Credit Card Number"
            // pattern="[\d| ]{16,22}"
            maxLength={16}
            onChange={handleCCInputChange}
            onFocus={handleInputFocus}
            // style={{ boxShadow: isValid=="valid" ? 'inset 0 0 10px green' : (isValid=="neutral" ?'inset 0 0 10px red':'') }} 
            style={{ boxShadow: isValid===true ? 'inset 0 0 10px green' : (!isValid ? 'inset 0 0 10px red':'')}} 
            />
          <button className='validate-button' onClick={handleValidate}>Validate</button>
        </div>
        <div className="form-row">
          <div className="first-name-field">
            <input
              type="tel"
              name="firstName"
              className="credit-card-input-2"
              placeholder="First Name"
              onChange={handleInputChange}
              onFocus={handleInputFocus}
            />
          </div>
          <div className="last-name-field">
            <input
              type="tel"
              name="lastName"
              className="credit-card-input-2"
              placeholder="Last Name"
              onChange={handleInputChange}
              onFocus={handleInputFocus}
            />
          </div>
        </div>
        <div className="form-row">
          <div className="exp-field">
            <input
              type="tel"
              name="expiry"
              className="credit-card-input-2"
              placeholder="Expiry YYMM"
              // pattern="\d\d/\d\d"
              maxLength={4}
              onChange={handleInputChange}
              onFocus={handleInputFocus}
            />
          </div>
          <div className="cvs-field">
            <input
              type="tel"
              name="cvc"
              className="credit-card-input-2"
              placeholder="CVC"
              // pattern="\d{3,4}"
              maxLength={3}
              onChange={handleInputChange}
              onFocus={handleInputFocus}
            />
          </div>
        </div>
        <div className="form-actions">
          <button onClick={handleSubmit} className="credit-card-button">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default PaymentForm;