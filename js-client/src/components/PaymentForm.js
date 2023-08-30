import React, { useState } from 'react';
import Cards from 'react-credit-cards-2';
import 'react-credit-cards-2/dist/es/styles-compiled.css';
import './PaymentForm.css';

function PaymentForm(){
  const [state, setState] = useState({
    number: '',
    expiry: '',
    cvc: '',
    firstName:'',
    lastName:'',
    focus: '',
  });

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;
    setState((prev) => ({ ...prev, [name]: value }));
  }

  const handleInputFocus = (evt) => {
    setState((prev) => ({ ...prev, focus: evt.target.name }));
  }
  const handleSubmit = () => {

  }
  const handleValidate =()=>{

  }
  return (
    <div className="payment-form-container">
      <div className="credit-card-container">
        <Cards
          number={state.number}
          expiry={state.expiry}
          cvc={state.cvc}
          name={state.firstName+" "+state.lastName}
          focused={state.focus}
        />
      </div>
      <form onSubmit={handleSubmit} className='form'>
        <div className="credit-card-field">
          <input
            type="tel"
            name="number"
            className="credit-card-input"
            placeholder="Credit Card Number"
            // pattern="[\d| ]{16,22}"
            required
            maxLength={16}
            onChange={handleInputChange}
            onFocus={handleInputFocus}
          />
          <button className='validate-button'>Validate</button>
        </div>
        <div className="form-row">
          <div className="first-name-field">
            <input
              type="tel"
              name="firstName"
              className="credit-card-input-2"
              placeholder="First Name"
              required
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
              required
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
              required
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
              required
              onChange={handleInputChange}
              onFocus={handleInputFocus}
            />
          </div>
        </div>
        <div className="form-actions">
          <button className="credit-card-button">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default PaymentForm;